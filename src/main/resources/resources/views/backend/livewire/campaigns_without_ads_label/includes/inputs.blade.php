<div class="form-group row">
    {{ html()->label('Campaign Id <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('campaign_id') }}
    <div class="col-sm-10">
        <input type="text" wire:model.defer="campaignWithoutAdsLabel.campaign_id" class="form-control">
        @error('campaignWithoutAdsLabel.campaign_id')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>
