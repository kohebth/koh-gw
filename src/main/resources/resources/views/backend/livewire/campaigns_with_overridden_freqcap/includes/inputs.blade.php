<div class="form-group row">
    {{ html()->label('Campaign Id <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('campaign_id') }}
    <div class="col-sm-10">
        <input type="text" wire:model.defer="campFreqCapOverride.campaign_id" class="form-control">
        @error('campFreqCapOverride.campaign_id')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>

<div class="form-group row">
    {{ html()->label('Freq Cap <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('freq_cap') }}
    <div class="col-sm-10">
        <input type="text" wire:model.defer="campFreqCapOverride.freq_cap" class="form-control">
        @error('campFreqCapOverride.freq_cap')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>
